package com.wty.hfm;

/**
 * 哈夫曼树结点
 */
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {
 
    /**
     * 数据域
     */
    private Byte data;
 
    /**
     * 结点权值
     */
    private Integer weight;
 
    /**
     * 左子节点
     */
    private HuffmanTreeNode left;
 
    /**
     * 右子节点
     */
    private HuffmanTreeNode right;
 
    public Integer getWeight() {
        return weight;
    }
 
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
 
    public HuffmanTreeNode getLeft() {
        return left;
    }
 
    public void setLeft(HuffmanTreeNode left) {
        this.left = left;
    }
 
    public HuffmanTreeNode getRight() {
        return right;
    }
 
    public void setRight(HuffmanTreeNode right) {
        this.right = right;
    }
 
    public Byte getData() {
        return data;
    }
 
    public void setData(Byte data) {
        this.data = data;
    }
 
    public HuffmanTreeNode(Integer value) {
        weight = value;
    }
 
    public HuffmanTreeNode(Byte data, Integer value) {
        this.weight = value;
        this.data = data;
    }
 
    @Override
    public String toString() {
        return "Node[" +
                "data=" + (data == null ? null : (char) data.byteValue()) +
                ", weight=" + weight +
                ']';
    }
 
    /**
     * 重写Comparable接口，比较结点关键字的大小
     *
     * @param node
     * @descriptions:
     *   (1) 重写的compareTo关系到Collections.sort()如何排序
     *   (2) 若为【当前对象属性 - 传入对象属性】，则为升序
     *   (3) 若为【传入对象属性 - 当前对象属性】，则为降序
     * @return
     */
    @Override
    public int compareTo(HuffmanTreeNode node) {
        return this.weight - node.weight;
    }
 
 
    /**
     * 先序遍历
     */
    public void preOrder() {
        // 访问当前结点
        System.out.println(this.toString());
        // 遍历左子树
        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        // 遍历右子树
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
    }
}