package com.lzumetal.javalearn.struct.tree;



import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liaosi
 * @date 2022-01-07
 */
public class BinaryTree {


    /**
     * 二叉树的二叉链表结点结构
     */
    static class BiTNode<T> {

        /* 节点数据 */
        private T data;

        /* 左右孩子指针 */
        private BiTNode lchild, rchild;

        public BiTNode() {
        }

        public <E> BiTNode(E e) {
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public BiTNode getLchild() {
            return lchild;
        }

        public void setLchild(BiTNode lchild) {
            this.lchild = lchild;
        }

        public BiTNode getRchild() {
            return rchild;
        }

        public void setRchild(BiTNode rchild) {
            this.rchild = rchild;
        }
    }


    /**
     * 二叉树的层序遍历算法
     *
     * @param root
     */
    public static void layerOrderTraverse(BiTNode root) {
        if (root == null) {
            return;
        }
        //层序遍历
        Queue<BiTNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BiTNode node = queue.poll();
            System.out.print(node.getData());
            BiTNode lchild = node.getLchild();
            if (lchild != null) {
                queue.add(lchild);
            }
            BiTNode rchild = node.getRchild();
            if (rchild != null) {
                queue.add(rchild);
            }
        }
    }


    /**
     * 创建一棵二叉树
     *
     * @param preOrder 二叉树的先序序列
     * @param inOrder  二叉树的中序序列
     * @return 返回二叉树的根结点
     */
    public static <E> BiTNode<E> buildTree(E[] preOrder, E[] inOrder) {
        if (preOrder == null || preOrder.length == 0) {
            return null;
        }
        BiTNode<E> root = new BiTNode<>(preOrder[0]);
        //找到inOrder中的root的位置
        int inOrderIndex = 0;
        for (char i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == root.getData()) {
                inOrderIndex = i;
            }
        }
        //preOrder的左子树和右子树部分
        E[] preOrderLeft = Arrays.copyOfRange(preOrder, 1, 1 + inOrderIndex);
        E[] preOrderRight = Arrays.copyOfRange(preOrder, 1 + inOrderIndex, preOrder.length);

        //inOrder的左子树和右子树部分
        E[] inOrderLeft = Arrays.copyOfRange(inOrder, 0, inOrderIndex);
        E[] inOrderRight = Arrays.copyOfRange(inOrder, inOrderIndex + 1, inOrder.length);

        //递归建立左子树和右子树
        BiTNode leftChild = buildTree(preOrderLeft, inOrderLeft);
        BiTNode rightChild = buildTree(preOrderRight, inOrderRight);

        root.setLchild(leftChild);
        root.setRchild(rightChild);
        return root;
    }

}
