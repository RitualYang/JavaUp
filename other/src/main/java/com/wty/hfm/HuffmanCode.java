package com.wty.hfm;

import java.util.*;
 
/**
 * 哈夫曼编码
 */
public class HuffmanCode {
 
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        // 字符串转为byte数组
        String content = "dXNlcklkPTRmNDI1MDRhNzA0YTZiNTc2ODZiNjIzNzZlNTk2NjM5N2EzMTU0ODU5NTY3M2QzZCZhY3Rpdml0eUNvZGU9NDEyMDIxMDkyNzAxMCZzdG9yZUNvZGU9MTIzJmNpdHlDb2RlPTEwMTEwMDAmdGltZXN0YW1wPTE2MzQwMDgyOTQwNjg=";
        System.out.printf("原始长度为 %d\n", content.length());
        byte[] bytes = content.getBytes();
        // 哈夫曼压缩
        byte[] huffmanCodes = huffmanCompress(bytes);
        System.out.println(new String(huffmanCodes).length());
        // 哈夫曼解压缩
        byte[] decodeResult = decompress(codeTable, huffmanCodes);
        System.out.println(huffmanCodes + " ||||" + new String(decodeResult));
    }
 
 
    /**
     * 编码表
     */
    private static Map<Byte, String> codeTable;
 
    /**
     * 暂存可能不足一个字节的字符编码
     */
    private static String lastByte = "";
 
    /**
     * 将原始byte数组进行哈夫曼压缩
     *
     * @param bytes 原始数组
     * @return 经过哈夫曼压缩的byte数组
     */
    public static byte[] huffmanCompress(byte[] bytes) {
        List<HuffmanTreeNode> nodeList = getNodes(bytes);
        HuffmanTreeNode root = HuffmanTree.createHuffTree(nodeList);
        codeTable = HuffmanTree.getCodingTable(root);
        return transfer(bytes, codeTable);
    }
 
 
    /**
     * 将byte数组转为哈夫曼树结点的list
     *
     * @param bytes byte数组
     * @return 结点list
     */
    private static List<HuffmanTreeNode> getNodes(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>(32);
        for (byte b : bytes) {
            map.merge(b, 1, Integer::sum);
        }
 
        List<HuffmanTreeNode> nodeList = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodeList.add(new HuffmanTreeNode(entry.getKey(), entry.getValue()));
        }
        return nodeList;
    }
 
 
    /**
     * 将原始byte数组根据编码表转换为哈夫曼编码
     *
     * @param codeTable 编码表
     * @param origin    原始数组
     * @return 哈夫曼编码后的byte数组
     */
    private static byte[] transfer(byte[] origin, Map<Byte, String> codeTable) {
        StringBuilder huffmanStr = new StringBuilder();
        for (byte item : origin) {
            huffmanStr.append(codeTable.get(item));
        }
        int len = huffmanStr.length() / 8;
        if (huffmanStr.length() % 8 != 0) {
            lastByte = huffmanStr.substring(len * 8);
        }
        byte[] huffmanCode = new byte[len];
        for (int i = 0; i < huffmanCode.length; i++) {
            huffmanCode[i] = (byte) Integer.parseInt(huffmanStr.substring(i * 8, i * 8 + 8), 2);
        }
        return huffmanCode;
    }
 
 
    /**
     * 封装解压缩的方法
     *
     * @param bytes 经过压缩的byte数组
     * @return 解压后的byte数组
     */
    public static byte[] decompress(byte[] bytes) {
        return decompress(codeTable, bytes);
    }
 
 
    /**
     * 将一个byte转为二进制字符串
     *
     * @param b 一个byte
     * @return b对应的二进制字符串（补码）
     */
    private static String byteToString(byte b) {
        int temp = b;
        temp |= 256;
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);
    }
 
 
    /**
     * 对压缩数据进行解码
     *
     * @param codeTable   编码表
     * @param huffmanCode 压缩的数组
     * @return 原字符串对应的byte数组
     */
    private static byte[] decompress(Map<Byte, String> codeTable, byte[] huffmanCode) {
        System.out.println(Arrays.toString(huffmanCode));
        StringBuilder stringBuilder = new StringBuilder();
        for (byte value : huffmanCode) {
            stringBuilder.append(byteToString(value));
        }
        stringBuilder.append(lastByte);
        Map<String, Byte> map = new HashMap<>(32);
        for (Map.Entry<Byte, String> entry : codeTable.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> byteList = new ArrayList<>();

        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            Byte b;
            while (true) {
                String str = stringBuilder.substring(i, i + count);
                b = map.get(str);
                if (b != null) {
                    byteList.add(b);
                    i += count;
                    break;
                }
                count++;
            }
        }
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteList.get(i);
        }
        return bytes;
    }
}