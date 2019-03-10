package com.smile.azxx.model;

import com.smile.azxx.entity.sysmng.Resource;

import java.util.List;

/**
 * Created by smile on 2018/4/13.
 */
public class TreeResource extends Resource{
    private List<TreeResource> children;

    private List<TreeResource> nodes;

    private String text;

    public List<TreeResource> getChildren() {
        return children;
    }

    public void setChildren(List<TreeResource> children) {
        this.children = children;
    }

    public List<TreeResource> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeResource> nodes) {
        this.nodes = nodes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
