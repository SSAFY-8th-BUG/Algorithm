import java.util.*;

public class PG_4_tableMinPark {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(6, new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}}, new int[]{1, 3}, new int[]{5})));
    }

    static List<List<P>> graph;

    static class P {
        int next, weight; // 노드, 가중치

        P(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());

        for (int[] path : paths) {
            int start = path[0];
            int end = path[1];
            int weight = path[2];

            if (isGate(start, gates) || isSummit(end, summits)) {
                graph.get(start).add(new P(end, weight));
            } else if (isGate(end, gates) || isSummit(start, summits)) {
                graph.get(end).add(new P(start, weight));
            } else {
                graph.get(start).add(new P(end, weight));
                graph.get(end).add(new P(start, weight));
            }
        }
        return dijkstra(n, gates, summits);
    }

    static int[] dijkstra(int n, int[] gates, int[] summits) {
        Queue<P> qu = new LinkedList<>();
        int[] intensity = new int[n + 1];

        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gates) {
            qu.add(new P(gate, 0));
            intensity[gate] = 0;
        }

        while (!qu.isEmpty()) {
            P cn = qu.poll();

            if(cn.weight > intensity[cn.next]) continue;

            for (int i = 0; i < graph.get(cn.next).size(); i++) {
                P nn = graph.get(cn.next).get(i);

                int dis = Math.max(intensity[cn.next], nn.weight);
                if (intensity[nn.next] > dis) {
                    intensity[nn.next] = dis;
                    qu.add(new P(nn.next, dis));
                }
            }
        }

        int mn = Integer.MAX_VALUE;
        int mw = Integer.MAX_VALUE;

        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < mw) {
                mn = summit;
                mw = intensity[summit];
            }
        }

        return new int[]{mn, mw};
    }

    static boolean isGate(int num, int[] gates) {
        for (int gate : gates) {
            if (num == gate) return true;
        }
        return false;
    }

    static boolean isSummit(int num, int[] submits) {
        for (int submit : submits) {
            if (num == submit) return true;
        }
        return false;
    }
}