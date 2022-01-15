package com.lzumetal.javalearn.struct.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liaosi
 * @date 2022-01-07
 */
public class BinaryTree {


    /**
     * 二叉树的二叉链表结点结构
     */
    @Getter
    @Setter
    class BiTNode<T> {

        /* 节点数据 */
        private T data;

        /* 左右孩子指针 */
        private BiTNode lchild, rchild;


    }


    /**
     * 二叉树的前序遍历递归算法
     *
     * @param root
     */
    public void preOrderTraverse(BiTNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getData()); //访问节点
        preOrderTraverse(root.getLchild()); //再先序遍历左子树
        preOrderTraverse(root.getRchild()); //最后先序遍历右子树
    }


    /**
     * 二叉树的中序遍历递归算法
     *
     * @param root
     */
    public void inOrderTraverse(BiTNode root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.getLchild()); //中序遍历左子树
        System.out.println(root.getData()); //访问节点
        inOrderTraverse(root.getRchild()); //最后中序遍历右子树
    }


    /**
     * 二叉树的后序遍历递归算法
     *
     * @param root
     */
    public void postOrderTraverse(BiTNode root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.getLchild()); //先后序遍历左子树
        postOrderTraverse(root.getRchild()); //再后序遍历右子树
        System.out.println(root.getData()); //访问节点
    }
}
