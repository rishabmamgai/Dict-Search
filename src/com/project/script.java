package com.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.io.File;


class Dictionary {
    public TrieNode head = new TrieNode();
    public List<String> ans = new ArrayList<>();

    static class TrieNode {
        public TrieNode[] table;
        public boolean end;

        TrieNode() {
            this.table = new TrieNode[26];
            this.end = false;

            Arrays.fill(this.table, null);
        }
    }

    public void addWord(String word) {
        TrieNode temp = head;

        for(int i=0; i<word.length(); ++i) {
            char ch = word.charAt(i);

            TrieNode node = temp.table[ch % 97];

            if(node == null) {
                node = new TrieNode();
                temp.table[ch % 97] = node;
            }

            temp = node;
        }

        temp.end = true;
    }

    public void searchWord(String word) {
        searchTrie(head, word, 0, "");
    }

    public void searchTrie(TrieNode root, String word, int i, String str) {
        if(root.end)
            ans.add(str);

        if(i < word.length()) {
            char ch = word.charAt(i);

            if (ch == '.') {
                for (int j = 0; j < root.table.length; ++j) {
                    if (root.table[j] != null)
                        searchTrie(root.table[j], word, i + 1, str + (char)(j + 97));
                }
            }

            else {
                if (root.table[ch % 97] != null)
                    searchTrie(root.table[ch % 97], word, i + 1, str + ch);
            }
        }
    }
}


public class script {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary();

        try {
            File myFile = new File("D:\\Java programmes\\Winter Training\\words.txt");
            Scanner reader = new Scanner(myFile);

            while (reader.hasNextLine()) {
                String word = reader.nextLine();
                dict.addWord(word);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        String search = sc.nextLine();
        dict.searchWord(search);
        System.out.println(dict.ans);
    }
}
