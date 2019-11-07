package utils;

import java.util.List;

/**
 *
 * @author Jerson
 */
public class Pagination<T> {
    
    private int count_page;
    private int countXpage;
    private int current_page;
    private int first_page;
    private int last_page;
    private int next;
    private int prev;
    private List<T> data;

    public int getCount_page() {
        return count_page;
    }

    public void setCount_page(int count_page) {
        this.count_page = count_page;
    }

    public int getCountXpage() {
        return countXpage;
    }

    public void setCountXpage(int countXpage) {
        this.countXpage = countXpage;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getFirst_page() {
        return first_page;
    }

    public void setFirst_page(int first_page) {
        this.first_page = first_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Pagination{" + "count_page=" + count_page + ", countXpage=" + countXpage + ", current_page=" + current_page + ", first_page=" + first_page + ", last_page=" + last_page + ", next=" + next + ", prev=" + prev + ", data=" + data + '}';
    }
    
    
    
}
