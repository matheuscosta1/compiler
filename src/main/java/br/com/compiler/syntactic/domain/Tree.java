package br.com.compiler.syntactic.domain;

import java.util.*;

public class Tree {
    private String name;
    private String attribute;
    private final List<Tree> children = new ArrayList<>();

    private final Map<String, Tree> nodeMap = new HashMap<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Tree() {
    }

    public Tree(String name, String attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    public Tree(String name) {
        this.name = name;
    }

    public void addChild(Tree child) {
        children.add(child);
    }

    public void addChild(String parentName, String childName, String attribute) {
        Tree parent = nodeMap.get(parentName);
        if (parent == null) {
            parent = new Tree(parentName);
            nodeMap.put(parentName, parent);
        }

        Tree child = new Tree(childName, attribute);
        parent.addChild(child);
        nodeMap.put(childName, child);
    }

    public String getName() {
        return name;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public Tree getRoot(String root) {
        return nodeMap.get(root);
    }

    public void printTree(String root) {
        printTree(getRoot(root), "", true);
    }

    private static void printTree(Tree node, String prefix, boolean isLast) {
        System.out.print(prefix);
        System.out.print(isLast ? "└── " : "├── ");
        System.out.println(node.getName());

        List<Tree> children = node.getChildren();

        for (int i = 0; i < children.size() - 1; i++) {
            Tree child = children.get(i);
            printTree(child, prefix + (isLast ? "    " : "│   "), false);
        }

        if (!children.isEmpty()) {
            Tree lastChild = children.get(children.size() - 1);
            printTree(lastChild, prefix + (isLast ? "    " : "│   "), false);
        }
    }
}
