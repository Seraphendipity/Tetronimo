# Tetris Rendering
*Elijah T.he Rose (elirose)*

*CS203 - FA2019 - Dr. Unan*

## Overview
Render a singular tetris piece at random with random positioning and color.

## Implementation

### Random Numbers
Java's `Random` class was used for several integers, with varying ranges based on each other. That is, to try to minimize how many pieces showed up partially or even entirely out of bounds, the max random size of the tetronimo individual squares is set to be the difference of the full frame and the random displacement from the center. It does have the unfortunate result of sometimes very small tetronimoes, but something must give -- the randomness of displacement, frame size, tetronimo size, if we can view them, etc. thus I chose size.

Other than that, the random numbers are rather unremarkable in the `TetrisTester` class, simply then being fed to `TetrisComponent`, but do play a more interesting role within said class...

### Generating Tetris Pieces
Rather than a hard-coded set of each tetronimo possibility, a generic square creator is looped through:

```java
    for(int i = 0; i < 4; i++) {
        r.add(new Rectangle(xs+(xi*size),ys+(yi*size),
            size,size));
        g.setColor(color);
        g.fill(r.get(i));
        g.setColor(Color.black);
        g.draw(r.get(i));
        do {
        xi = rand.nextInt(3)-1; // -1|0|1
        yi = rand.nextInt(3)-1; // -1|0|1
        } while ( ((xi+yi)*(xi+yi)!=1) || ( (xo==-xi) && (yo==-yi) ) );
        xs += xo*size; ys += yo*size;
        xo = xi; yo = yi;
        //CO-1: ABS(SUM(x,y))==1
        //CO-2: (x,y)!=(xNew,yNew)
        color = color.darker();
    }
```

In essence, a random"jump" is decided -- up, down, left, or right -- checked to make sure it is valid (not in-place, diagonal, nor back into its previous move), and then used to create a new square, which then acts as the "home square" where the process repeats. This allows each tetronimo to be formed randomly. To help illustrate this process, the color darkens each step, allowing you to see the "original" square (the lightest shade) and what path it took to the last piece. Note how this square indicates that it started on its lower-left square, moved right, then up, then left again.

![Tetris square](tSquare)

The beauty of this solution is its genericness -- this can be abstracted to any N-gon, hence the `NtrisComponent`, which merely changes `i < 4` to `i < shape`, with shape thusly representing how many squares are desired. This allows for some interesting patterns...

![Septrimino Piece][sept]

![Septrimino Piece][sept]

There are problems with it, of course, such as anything above `shape = 4` can theoretically overwrite itself by branching back. Additionally, perhaps as per the game, there is not an even distribution of pieces. Each piece still is the same up to the second block. From the second block's perspective with the first block as back/ground (and then the third block as back/ground):
* Straight
  * Straight - I
  * Left - L
  * Right - iL
* Left
  * Left - Square
  * Straight - iL
  * Right - iZ
* Right
  * Left - Z
  * Straight - L
  * Right - Square
  * 
Note the ratio: Square|L|iL are twice as likely to appear as I|Z|iZ.

Lastly, it was not till after this analysis that I realized T pieces were completely unaccounted for.

Problem: longer ones can feed back in Ntris

---

[tSquare]: 