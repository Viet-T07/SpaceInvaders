# SpaceInvaders

We need a:

- Vector class
- GameObject class <-- extends Node
- Laser class <-- extends GameObject
- Shooter class <-- extends GameObject as well (this one if for both player and enemies)
- Player class and Enemy class <-- extends Shooter (we might need Enemy to extend Node too so we'll make GameObject a Node)

`Shooter` has a `.shoot()` method that fires off a projectile. Upwards if player, downwards if enemy.

Grid movement is repeat(4 shifts right, down, 4 shift left, down).

Only the enemy in front will be the one shooting **or** we randomize shooting across the grid and disable friendly fire. Whichever option we go with, shooting is randomized: either across the whole grid or only among the bottom row.

We can use a `GridPane` for our group of enemies. GridPane allows us to manipulate columns and rows, so instead of using nested for loops we can move the rows and columns accordingly.

In practice it would look like:
- Shift every column right 4 times
- Shift every row down
- Shift every column right 4 times
- Shift every row down
