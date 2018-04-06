package com.playmoweb.nestedadapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Node
 *
 * @author Thibaud Giovannetti
 * @by Playmoweb
 * @created 21/07/2017
 */
public class Node {
    SectionAdapter adapter = null;
    List<Node> child = null;

    public Node() {
    }

    public Node(final SectionAdapter adapter) {
        this.adapter = adapter;
    }

    public SectionAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(final SectionAdapter adapter) {
        this.adapter = adapter;
    }

    public List<Node> getChilds() {
        return child;
    }

    public Node addChild(final Node toAdd) {
        if (child == null) {
            child = new ArrayList<>();
        }
        child.add(toAdd);
        return this;
    }
}
