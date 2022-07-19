package com.knubisoft.base.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TreeTasksImpl implements TreeTasks {

    @Override
    public boolean isSameTree(TreeNode node1, TreeNode node2) {
        return node1.equals(node2);
    }

    @Override
    public List<Integer> inorderTraversal(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        if (node.left == null) {
            result.add(node.val);
            if (node.right != null) {
                result.addAll(inorderTraversal(node.right));
            }
            return result;
        }
        result.addAll(inorderTraversal(node.left));
        result.add(node.val);
        if (node.right != null) {
            result.addAll(inorderTraversal(node.right));
        }
        return result;
    }


    @Override
    public boolean isSymmetric(TreeNode node) {
        String[][] strings = Stream.of(inorderTraversal(node.left), inorderTraversal(node.right))
                .map(stream -> stream.stream()
                        .map(String::valueOf)
                        .toArray(String[]::new))
                .toArray(String[][]::new);
        StringBuilder builder = new StringBuilder();
        for (int i = strings[1].length - 1; i >= 0; i--) {
            builder.append(strings[1][i]);
        }
        String firstTree = Arrays.stream(strings[0]).reduce((x, y) -> x + y).orElseThrow();
        return firstTree.equals(builder.toString());
    }

    @Override
    public int maxDepth(TreeNode node) {
        int count = 1;
        int countLeft = 0;
        int countRight = 0;
        if (node.left != null) {
            countLeft = maxDepth(node.left);
        }
        if (node.right != null) {
            countRight = maxDepth(node.right);
        }
        return count + (Math.max(countLeft, countRight)) ;
    }

    @Override
    public boolean hasPathSum(TreeNode node, int targetSum) {
        boolean right = false;
        boolean left = false;
        if (node.left == null && node.right == null) {
            return targetSum - node.val == 0;
        }
        if (node.left != null) {
            left = hasPathSum(node.left, targetSum - node.val);
        }
        if (node.right != null) {
            right = hasPathSum(node.right, targetSum - node.val);
        }
        return right || left;
    }

    @Override
    public TreeNode invertTree(TreeNode node) {
        if (node.left != null) {
            node.left = invertTree(node.left);
        }
        if (node.right != null) {
            node.right = invertTree(node.right);
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        return node;
    }

    @Override
    public int sumOfLeftLeaves(TreeNode node) {
        int left = 0;
        int right = 0;
        if (node.left == null && node.right == null) {
            return node.val;
        }
        if (node.left != null) {
            left = sumOfLeftLeaves(node.left);
        }
        if (node.right != null && node.right.left != null) {
            right = sumOfLeftLeaves(node.right);
        }
        return left + right;
    }

    @Override
    public TreeNode mergeTrees(TreeNode node1, TreeNode node2) {
        TreeNode newLeft = null;
        TreeNode newRight = null;
        if (node1.left != null && node2.left != null) {
            newLeft = mergeTrees(node1.left, node2.left);
        }
        if (node1.left != null && node2.left == null) {
            newLeft = node1.left;
        }
        if (node2.left != null && node1.left == null) {
            newLeft = node2.left;
        }
        if (node1.right != null && node2.right != null) {
            newRight = mergeTrees(node1.right, node2.right);
        }
        if (node1.right != null && node2.right == null) {
            newRight = node1.right;
        }
        if (node2.right != null && node1.right == null) {
            newRight = node2.right;
        }
        return new TreeNode(node1.val + node2.val, newLeft, newRight);
    }
}
