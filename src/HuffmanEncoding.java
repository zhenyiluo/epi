import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class HuffmanEncoding
{
    public static class Symbol{
        char c;
        double prob;
        String code;
    }

    public static class BinaryTree{
        BinaryTree left;
        BinaryTree right;
        double weight;
        Symbol symbol;
        public BinaryTree(double aInWeight, Symbol aInSymbol, BinaryTree aInLeft, BinaryTree aInRight){
            weight = aInWeight;
            symbol = aInSymbol;
            left = aInLeft;
            right = aInRight;
        }
    }
    
    public static void huffmanEncoding(List<Symbol> symbols){
        PriorityQueue<BinaryTree> minHeap = new PriorityQueue<BinaryTree>(10, new Comparator<BinaryTree>(){
            @Override
            public int compare(BinaryTree bt1, BinaryTree bt2){
                return Double.compare(bt1.weight, bt2.weight);
            }
        });

        for(Symbol symbol :symbols){
            minHeap.add(new BinaryTree(symbol.prob, symbol, null, null));
        }

        while(minHeap.size() > 1){
            BinaryTree l = minHeap.poll();
            BinaryTree r = minHeap.poll();
            minHeap.add(new BinaryTree(l.weight + r.weight, null, l, r));
        }

        assignHuffmanCode(minHeap.peek(), "");

    }

    public static void assignHuffmanCode(BinaryTree node, String code){
        if(node == null){
            return;
        }

        if(node.symbol != null){
            node.symbol.code = code;
        }else{
            assignHuffmanCode(node.left, code + "0");
            assignHuffmanCode(node.right, code + "1");
        }
    }


    //    private static final double[] ENGLISH_FREQ = {
//            8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966,
//            0.153, 0.772, 4.025, 2.406, 6.749,  7.507, 1.929, 0.095, 5.987,
//            6.327, 9.056, 2.758, 0.978, 2.360,  0.150, 1.974, 0.074};

    //    public static void main(String[] args) {
//        int n;
//        Random r = new Random();
//        if (args.length == 1) {
//            if (!"huffman".equals(args[0])) {
//                n = Integer.parseInt(args[0]);
//            } else {
//                n = 26;
//            }
//        } else {
//            n = r.nextInt(255) + 1;
//        }
//        List<Symbol> symbols = new ArrayList<Symbol>();
//        int sum = 0;
//        if (args.length == 0 || (!"huffman".equals(args[0]))) {
//            for (int i = 0; i < n; ++i) {
//                Symbol t = new Symbol();
//                t.c = (char)i;
//                t.prob = r.nextInt(100001);
//                sum += t.prob;
//                symbols.add(t);
//            }
//            for (int i = 0; i < n; ++i) {
//                symbols.get(i).prob /= sum;
//            }
//        } else {
//            for (int i = 0; i < n; ++i) {
//                Symbol t = new Symbol();
//                t.c = (char)('a' + i);
//                t.prob = ENGLISH_FREQ[i];
//                symbols.add(t);
//            }
//        }
//        huffmanEncoding(symbols);
//        double avg = 0.0;
//        for (Symbol symbol : symbols) {
//            System.out.println(symbol.c);
//            avg += symbol.prob / 100 * symbol.code.length();
//        }
//        System.out.println("average huffman code length = " + avg);
//    }
}