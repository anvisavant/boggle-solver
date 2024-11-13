class DictionaryTree {
    private TreeNode root;

    public DictionaryTree() {
        this.root = new TreeNode('\0'); //starts with null
    }

    public void insert(String word) {
        TreeNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TreeNode(c));
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }

    public boolean startsWith(String prefix) {
        TreeNode node = findNode(prefix);
        return node != null;
    }

    public boolean search(String word) {
        TreeNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    private TreeNode findNode(String str) {
        TreeNode current = root;
        for (char c : str.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return null;
            }
            current = current.children.get(c);
        }
        return current;
    }
}
