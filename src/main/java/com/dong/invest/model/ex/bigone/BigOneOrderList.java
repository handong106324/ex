package com.dong.invest.model.ex.bigone;

import java.util.List;

public class BigOneOrderList {
    private BigOnePageInfo page_info;

    private List<BigOneOrderNode> edges;

    public BigOnePageInfo getPage_info() {
        return page_info;
    }

    public void setPage_info(BigOnePageInfo page_info) {
        this.page_info = page_info;
    }

    public List<BigOneOrderNode> getEdges() {
        return edges;
    }

    public void setEdges(List<BigOneOrderNode> edges) {
        this.edges = edges;
    }


    @Override
    public String toString() {
        return "BigOneOrder{" +
                "page_info=" + page_info +
                ", edges=" + edges +
                '}';
    }
}
