class A {
    JManager jManager = new JManager();

    public static void main(String[] args) {
        new A().call();
    }

    private void call() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    while (true) {
                    jManager.accumulate();
//                    }
                }
            }).start();
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
//                            while (true) {
                            jManager.subact();
//                            }
                        }
                    }
            ).start();
        }
    }
}

class JManager {
    private int j = 0;

    public synchronized void subact() {
        j--;
        System.out.println(j);
    }

    public synchronized void accumulate() {
        j++;
        System.out.println(j);
    }
}