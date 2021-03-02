package com.wxw.domain;

public class Engines {
    private String engine;

    private String support;

    private String comment;

    private String transactions;

    private String xa;

    private String savepoints;

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine == null ? null : engine.trim();
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support == null ? null : support.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions == null ? null : transactions.trim();
    }

    public String getXa() {
        return xa;
    }

    public void setXa(String xa) {
        this.xa = xa == null ? null : xa.trim();
    }

    public String getSavepoints() {
        return savepoints;
    }

    public void setSavepoints(String savepoints) {
        this.savepoints = savepoints == null ? null : savepoints.trim();
    }
}