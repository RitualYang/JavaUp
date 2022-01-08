package com.wty.hfm;

import java.util.*;
 
/**
 * 哈夫曼树
 */
public class HuffmanTree {

    /**
     * 权值数组转为哈夫曼树
     *
     * @param arr 权值的数组
     * @return 结点list
     */
    public static List<HuffmanTreeNode> arrToList(int[] arr) {
        List<HuffmanTreeNode> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new HuffmanTreeNode(value));
        }
        return nodes;
    }

    /**
     * 创建哈夫曼树
     *
     * @param nodes 结点list
     * @return 创建的哈夫曼树的根
     */
    public static HuffmanTreeNode createHuffTree(List<HuffmanTreeNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            HuffmanTreeNode left = nodes.get(0);
            HuffmanTreeNode right = nodes.get(1);
            HuffmanTreeNode parent = new HuffmanTreeNode(left.getWeight() + right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    /**
     * 先序遍历
     *
     * @param root 根结点
     */
    public static void preOrder(HuffmanTreeNode root) {
        if (root == null) {
            System.out.println("树为空");
        } else {
            root.preOrder();
        }
    }

    /**
     * 字符与编码的映射
     */
    public static Map<Byte, String> codeMap = new HashMap<>(32);

    /**
     * 重载方法，根据根结点获取哈夫曼树的编码表
     *
     * @param root 根结点
     * @return 字符与编码的映射
     */
    public static Map<Byte, String> getCodingTable(HuffmanTreeNode root) {
        getCodingTable(root, "", new StringBuilder());
        return codeMap;
    }

    /**
     * 获取节点的哈夫曼编码并存入映射
     *
     * @param node     当前结点
     * @param path     路径（向左 0；向右 1）
     * @param lastCode 父结点的编码
     */
    private static void getCodingTable(HuffmanTreeNode node, String path, StringBuilder lastCode) {
        if (node != null) {
            StringBuilder curCode = new StringBuilder(lastCode);
            curCode.append(path);
            if (node.getData() == null) {
                getCodingTable(node.getLeft(), "0", curCode);
                getCodingTable(node.getRight(), "1", curCode);
            } else {
                codeMap.put(node.getData(), curCode.toString());
            }
        }
    }
}