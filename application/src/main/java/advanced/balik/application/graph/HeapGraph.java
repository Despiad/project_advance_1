package advanced.balik.application.graph;

import advanced.balik.application.model.LeftHeapNode;
import advanced.balik.application.model.LeftistHeap;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class HeapGraph {
    /**
     * Базовый радиус ячейки на графе.
     */
    private static final double CELL_RADIUS = 20;

    /**
     * Вертикальное расстояние между вершинами графа.
     */
    private static final double VERTICAL_GAP = CELL_RADIUS * 3;

    /**
     * Ячейки - группа вершин графа, предстваленных стилизованным {@link Label}.
     */
    private final Group cells;

    /**
     * Ребра - группа ребер графа, представленных {@link Line}.
     */
    private final Group vertexes;

    /**
     * Общая группа визуальных компонентов, образованная наложением {@link HeapGraph#cells} на
     * {@link HeapGraph#vertexes}.
     */
    private final Group content;

    /**
     * Кэш визуальных компонентов графа.
     */
    private final GraphCache cache = new GraphCache();

    private LeftistHeap tree;


    /**
     * Базовый конструктор - задает композицию групп визуальных компонентов.
     */
    //TODO:FACTORY
    public HeapGraph() {
        cells = new Group();
        vertexes = new Group();
        content = new Group(vertexes, cells);
        tree = new LeftistHeap();
    }

    /**
     * Добавить значение в кучу, перерисовать граф и выделить добавленную вершину.
     *
     * @param value целое число.
     */
    public void addNode(int value) {
        tree.insert(value);
        draw();
    }

    /**
     * Метод перерисовки графа.
     */
    private void draw() {
        scrap();
        display(tree);
    }

    /**
     * Метод отрисовки дерева.
     *
     * @param tree модель данных, на основе которой выполняется построение графа.
     */
    private void display(LeftistHeap tree) {
        Map<Integer, Position> levels = buildLevels(tree, new Position(0, 0));

        levels.forEach(this::drawCell);
        drawVertexes(tree);
    }

    /**
     * Метод построения карты позиций вершин графа на 2d системе координат.
     * <br>Горизонтальное расстояние между вершинами одного уровня
     * считается как 2^[высота поддерева] * {@link HeapGraph#CELL_RADIUS * 0.75}
     * <br>Вертикальное расстояние между вершинами соседних уровней = {@link HeapGraph#VERTICAL_GAP}
     * <br>Каждая запись в итоговой карте соответствует значению из дерева
     * и его позиции при отрисовке вершины графа на системе координат.
     * <br>Записи добавляются в ходе рекурсивного обхода дерева.
     *
     * @return карта позиций для каждого значения из дерева.
     */
    //todo: height of tree
    private Map<Integer, Position> buildLevels(LeftistHeap tree, Position position) {
        if (tree.isEmpty()) return Collections.emptyMap();

        Map<Integer, Position> nodeMap = new HashMap<>();

        Optional<LeftHeapNode> rootNode = Optional.ofNullable(tree.getRoot());

        rootNode.ifPresent(root -> nodeMap.put(root.getElement(), position));

        LeftistHeap left = new LeftistHeap(tree.getRoot().getLeft());
        LeftistHeap right = new LeftistHeap(tree.getRoot().getRight());

//        int height = right.getRoot().getsValue() > left.getRoot().getsValue()
//                ? right.getRoot().getsValue() : left.getRoot().getsValue();
        double horizontalGap = (CELL_RADIUS * 0.75) * (1L << 2); // fastest way to get a power of 2

        if (!left.isEmpty()) nodeMap.putAll(buildLevels(left, position.move(-horizontalGap, VERTICAL_GAP)));

        if (!right.isEmpty()) nodeMap.putAll(buildLevels(right, position.move(horizontalGap, VERTICAL_GAP)));

        return nodeMap;
    }

    /**
     * Метод отрисовки вершины графа в указанной позиции.
     *
     * @param value    значение в новой вершине.
     * @param position позиция новой вершины.
     * @see Position
     */
    private void drawCell(int value, Position position) {
        String text = String.valueOf(value);
        Label cell = cache.getCell(value, () -> {
            Label newCell = new Label(text);
            newCell.setId(text);
            newCell.getStyleClass().add(Style.CELL_STYLE.getStyleClass());
            newCell.setOnMouseClicked(e -> {
                String selected = Style.CELL_SELECTED_STYLE.getStyleClass();
                ObservableList<String> styleClass = newCell.getStyleClass();
                if (!styleClass.contains(selected)) {
                    unselect();
                    styleClass.add(selected);
                } else {
                    styleClass.remove(selected);
                }
            });
            return newCell;
        });
        cell.setLayoutX(position.x - CELL_RADIUS);
        cell.setLayoutY(position.y - CELL_RADIUS);
        cells.getChildren().add(cell);
    }

    /**
     * Метод отрисовки ребер графа по модели данных.
     * <br>Вызывется после отрисовки вершин графа.
     * <br>В качестве модели связности использует {@link LeftistHeap}.
     * <br>Ребра добавляются в ходе рекурсивного обхода дерева.
     *
     * @param tree {@link LeftistHeap}
     */
    private void drawVertexes(LeftistHeap tree) {
        Optional<LeftHeapNode> root = Optional.ofNullable(tree.getRoot());

        if (root.isPresent()) {
            int rootVal = root.get().getElement();

            LeftistHeap leftBranch = new LeftistHeap(root.get().getLeft());
            LeftistHeap rightBranch = new LeftistHeap(root.get().getRight());

            Optional<LeftHeapNode> right = Optional.ofNullable(rightBranch.getRoot());
            Optional<LeftHeapNode> left = Optional.ofNullable(leftBranch.getRoot());
            if (right.isPresent()) {
                drawVertex(rootVal, right.get().getElement());
                drawVertexes(rightBranch);
            }
            if (left.isPresent()) {
                drawVertex(rootVal, left.get().getElement());
                drawVertexes(leftBranch);
            }
        }

    }

    /**
     * Метод отрисовки ребра графа между двумя его вершинами с указанными значениями.
     *
     * @param start значение в первой вершине
     * @param end   значение во второй вершине.
     */
    private void drawVertex(int start, int end) {
        Label firstCell = (Label) cells.lookup("#" + start);
        if (firstCell == null) throw new IllegalStateException();

        Label secondCell = (Label) cells.lookup("#" + end);
        if (secondCell == null) throw new IllegalStateException();

        Line vertex = cache.getVertex(start, end, () -> {
            Line newVertex = new Line();
            newVertex.getStyleClass().add(Style.VERTEX.getStyleClass());
            newVertex.startXProperty().bind(firstCell.layoutXProperty().add(CELL_RADIUS));
            newVertex.startYProperty().bind(firstCell.layoutYProperty().add(CELL_RADIUS));
            newVertex.endXProperty().bind(secondCell.layoutXProperty().add(CELL_RADIUS));
            newVertex.endYProperty().bind(secondCell.layoutYProperty().add(CELL_RADIUS));
            return newVertex;
        });
        vertexes.getChildren().add(vertex);
    }

    /**
     * Убрать выделение с выделенной вершины графа.
     */
    private void unselect() {
        cells.getChildren()
                .forEach(node -> node.getStyleClass()
                        .removeIf(Predicate.isEqual(Style.CELL_SELECTED_STYLE.getStyleClass())));
    }

    /**
     * Метод очистки графа: очищает модель, визуальное предстваление и даже память!
     */
    public void clear() {
        tree.clear();
        scrap();
        cache.drop();
        System.gc(); //не делайте этого дома без надзора взрослых
    }


    /**
     * Проверка, является ли дерево пустым.
     *
     * @return результат проверки
     */
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * Метод очистки графа - удаляет все вершины и ребра.
     */
    private void scrap() {
        cells.getChildren().clear();
        vertexes.getChildren().clear();
    }

    /**
     * Геттер для группы визуальных компнентов графа.
     *
     * @return {@link HeapGraph#content}
     */
    public Group getContent() {
        return content;
    }


    private static final class GraphCache {

        private static final Map<String, Node> CACHE = new HashMap<>();

        private static final MessageFormat VERTEX_KEY_FORMAT = new MessageFormat("{0}-{1}");

        Label getCell(int value, Supplier<? extends Node> onAbsent) {
            String cellKey = String.valueOf(value);
            return (Label) CACHE.computeIfAbsent(cellKey, key -> onAbsent.get());
        }

        Line getVertex(int start, int end, Supplier<? extends Node> onAbsent) {
            String vertexKey = VERTEX_KEY_FORMAT.format(new Object[]{start, end});
            return (Line) CACHE.computeIfAbsent(vertexKey, key -> onAbsent.get());
        }

        void drop() {
            CACHE.clear();
        }
    }

    /**
     * Внутренний класс, определяющих 2d координаты точки.
     * Обьекты этого класса иммутабельны.
     */
    private final class Position {

        /**
         * Абсцисса точки.
         */
        private final double x;

        /**
         * Ордината точки.
         */
        private final double y;

        /**
         * Конструктор объектов класса {@code Position}.
         */
        Position(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Метод получения новой позиции,
         * полученной в результате смещения от текущей на указанные значения.
         *
         * @param x смещение по оси абсцисс
         * @param y смещение по оси ординат
         * @return новая позиция
         */
        Position move(double x, double y) {
            return new Position(this.x + x, this.y + y);
        }
    }

}
