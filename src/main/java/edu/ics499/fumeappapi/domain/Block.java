package edu.ics499.fumeappapi.domain;

import java.util.Calendar;

public class Block {
    private Integer hash;
    private Block top, left, right;
    private Block next;
    private Calendar time;
    private Transaction transaction;
    private int currenHeight;
    private static int height;

    public Block() {
        this.setTop(top);
        this.hash = hash.hashCode();
        this.setTemporal(Calendar.getInstance());
        height = 1;
        currenHeight = height;
    }

    public Block(Block next, Object hash) {
        height++;
        currenHeight = height;
        setNext(next);
        this.hash = hash.hashCode();
        this.setTemporal(Calendar.getInstance());
    }

    public Block(Block left, Block right, Object hash) {
        setLeft(left);
        setRight(right);
        this.hash = hash.hashCode();
        this.setTemporal(Calendar.getInstance());
    }
    /**
     * @return the hash
     */
    public Integer getHash() {return hash;}

    public Block getNext() {
        return next;
    }

    public void setNext(Block next) {
        this.next = next;
    }

    /**
     * @return the top
     */
    public Block getTop() {return top;}
    /**
     * @param top the top to set
     */
    public void setTop(Block top) {this.top = top;}

    public Block getLeft() {
        return left;
    }

    public void setLeft(Block left) {
        this.left = left;
    }

    public Block getRight() {
        return right;
    }

    public void setRight(Block right) {
        this.right = right;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Calendar getTemporal() {
        return time;
    }

    public void setTemporal(Calendar time) {
        this.time = time;
    }

    public int getCurrenHeight() {
        return currenHeight;
    }

    public void setCurrenHeight(int currenHeight) {
        this.currenHeight = currenHeight;
    }
}
