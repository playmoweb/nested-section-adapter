package com.playmoweb.nestedadapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Node
 * @author      Thibaud Giovannetti
 * @by          Playmoweb
 * @created     21/07/2017
 */
public class Node {
    SectionAdapter adapter = null;
    List<Node> child = null;

    public Node() { }

    public Node(SectionAdapter adapter) {
        this.adapter = adapter;
    }

    public SectionAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(SectionAdapter adapter) {
        this.adapter = adapter;
    }

    public List<Node> getChilds() {
        return child;
    }

    public Node addChild(Node toAdd) {
        if(child == null){
            child = new ArrayList<>();
        }
        child.add(toAdd);
        return this;
    }
}
