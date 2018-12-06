package advanced.balik.application.graph;

/**
 * Перечисление константных типов.
 * <br>Инкапсулирует CSS-классы, используемые приложением динамически.
 * <br>Константы должны соответствовать СSS-классам из файла "graph.css".
 *
 */
public enum Style {

    /**
     * Базовый стиль ребра дерева.
     */
    VERTEX("vertex"),

    /**
     * Базовый стиль ячейки дерева.
     */
    CELL_STYLE("cell"),

    /**
     * Стиль выбранной ячейки дерева.
     */
    CELL_SELECTED_STYLE("cell-selected");

    /**
     * Имя СSS-класса из graph.css
     */
    private final String styleClass;

    /**
     * Базовый конструктор
     */
    Style(String style) {
        styleClass = style;
    }

    /**
     * Геттер для {@link Style#styleClass}.
     *
     * @return {@link Style#styleClass}
     */
    public String getStyleClass() {
        return styleClass;
    }
}
