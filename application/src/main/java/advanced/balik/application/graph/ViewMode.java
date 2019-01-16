package advanced.balik.application.graph;

public enum ViewMode {
    changeTo16px(Style.CELL_16px, 22.5),
    STANDART(Style.CELL_STYLE, 20.0),
    changeTo12px(Style.CELL_12px, 17.5),
    changeTo10px(Style.CELL_10px, 15);

    private final Style style;
    private final double cellRadius;

    ViewMode(Style style, double cellRadius) {
        this.style = style;
        this.cellRadius = cellRadius;
    }

    public Style getStyle() {
        return style;
    }

    public double getCellRadius() {
        return cellRadius;
    }
}
