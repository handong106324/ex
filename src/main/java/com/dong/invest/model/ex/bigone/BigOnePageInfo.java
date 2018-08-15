package com.dong.invest.model.ex.bigone;

public class BigOnePageInfo {

    private String start_cursor;
    private boolean has_previous_page;
    private boolean has_next_page;
    private String end_cursor;

    public String getStart_cursor() {
        return start_cursor;
    }

    public void setStart_cursor(String start_cursor) {
        this.start_cursor = start_cursor;
    }

    public boolean isHas_previous_page() {
        return has_previous_page;
    }

    public void setHas_previous_page(boolean has_previous_page) {
        this.has_previous_page = has_previous_page;
    }

    public boolean isHas_next_page() {
        return has_next_page;
    }

    public void setHas_next_page(boolean has_next_page) {
        this.has_next_page = has_next_page;
    }

    public String getEnd_cursor() {
        return end_cursor;
    }

    public void setEnd_cursor(String end_cursor) {
        this.end_cursor = end_cursor;
    }

    @Override
    public String toString() {
        return "BigOnePageInfo{" +
                "start_cursor='" + start_cursor + '\'' +
                ", has_previous_page=" + has_previous_page +
                ", has_next_page=" + has_next_page +
                ", end_cursor='" + end_cursor + '\'' +
                '}';
    }
}
