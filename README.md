CellMatrix

===

A two dimensional cellular automata simulator.

How to Use
---

Firstly run CellMatrix and open the window.
```
java -jar jar/cellmatrix.jar
```

Initialize the cells by clicking them (most space of the panel is cells, a lot of them). Each cell you click will be turned black, which means a living one.

Another way to iniitialize the cells is to import an image. Click the 'Import' button.

Click 'Go', and the automachine starts. While running you can still click the cells or import an image.

To Customize
---

CellMatrix model can be passed into different "strategy", to define how cell evolve, how color is painted and how clicking on cells is handled.

In `org.accela.cellmatrix.strategy/`, there are multiple strategies to choose:

* EvolveStrategy
** BasicEvolveStrategy
** PopuEvolveStrategy

* ColorStrategy
** BasicColorStrategy
** PopuColorStrategy

* ClickStrategy
** BasicClickStrategy
** PopuClickStrategy

`org.accela.cellmatrix.Main` is the program entry, where you'll see

```
CellMatrix model = new CellMatrix(WIDTH, HEIGHT, new PopuEvolveStrategy());
CellPanel cellPanel = new CellPanel(model, new PopuColorStrategy(), new PopuClickStrategy());
```

`CellMatrix model` defines the logic model and `CellPanel cellPanel` defines how to display. The arguments are customizable, i.e. choose different strategies. You can also write your own ones.

