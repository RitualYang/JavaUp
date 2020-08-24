package com.easy;

import com.base.TreeNode;

/**
 * @author wty
 * @create 2020-03-03 22:11
 */
public class MirrorTree {
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null) {
            return null;
        }
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}
