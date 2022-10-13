package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA2383_3 {

	static int T, N, K, ans, max;

	static List<Ladder> ladder;
	static List<Person> person;
	static PriorityQueue<Person> pq1, pq2;
	
	static int capacity1, capacity2;
	
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb=new StringBuilder ();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			person = new ArrayList<>();
			ladder= new ArrayList<>();
			K=0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					if (n == 1)
						person.add(new Person(K++, i, j));
					else if (n > 1) {
						ladder.add(new Ladder (i, j, n));
					}
				}
			}
			
			selected=new boolean[K];
			pq1=new PriorityQueue<>( (Person p1, Person p2) -> p1.time-p2.time);
			pq2=new PriorityQueue<>( (Person p1, Person p2) -> p1.time-p2.time);
			
			ans=Integer.MAX_VALUE;
			makeTeam (0);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());

	}
	
	static int getDist (int y1, int x1, int y2, int x2) {
		return Math.abs(y1-y2)+Math.abs(x1-x2);
	}
	
	static void calc () {
		
		int y0=ladder.get(0).y;
		int y1=ladder.get(1).y;
		
		int x0=ladder.get(0).x;
		int x1=ladder.get(1).x;
		
		for (int i=0; i<K; i++) {

			int y=person.get(i).y;
			int x=person.get(i).x;
			
			if (selected[i]) {
				person.get(i).time=getDist(y0,x0,y,x);
				pq1.add(person.get(i));
			} else {
				person.get(i).time=getDist(y1,x1,y,x);
				pq2.add(person.get(i));
			}
		}
	}
	
	static void simulation () {
		
		capacity1=capacity2=max=0;
		int Time=0;		
		
		int[] finishTime=new int[K];

		while (!pq1.isEmpty() || !pq2.isEmpty()) {
			
			Time++;
			for (int i=0; i<K; i++) {
				if (finishTime[i]==Time) {
					finishTime[i]=0;
					if (selected[i]) capacity1--;
					else capacity2--;
				}
			}
			
			
			while (!pq1.isEmpty() && capacity1<3) {
				Person p=pq1.peek();
				if (p.time>Time) break;
 
				pq1.poll();
				if (p.time==Time) {
					finishTime[p.no]=Time+1+ladder.get(0).length;
				} else {
					finishTime[p.no]=Time+ladder.get(0).length;
				}
				capacity1++;
			}
			
			while (!pq2.isEmpty() && capacity2<3) {
				Person p=pq2.peek();
				if (p.time>Time) break;
 
				pq2.poll();
				if (p.time==Time) {
					finishTime[p.no]=Time+1+ladder.get(1).length;
				} else {
					finishTime[p.no]=Time+ladder.get(1).length;
				}
				capacity2++;
			}
		}
		
		max=Time;
		for (int i=0; i<K; i++)
			max=Math.max(max, finishTime[i]);
	}
	
	static void makeTeam (int tgtIdx) {
		if (tgtIdx==K) {	
			calc ();
			simulation();	
			ans=Math.min(ans, max);
			return ;
		}
		
		selected[tgtIdx]=true;
		makeTeam (tgtIdx+1);
		
		selected[tgtIdx]=false;
		makeTeam(tgtIdx+1);
	}

	static class Person {
		int no;
		int y, x, time;

		Person(int no, int y, int x) {
			this.no=no;
			this.y = y;
			this.x = x;
		}
	}
	
	static class Ladder {
		int y,x,length;
		
		Ladder (int y, int x, int length) {
			this.y=y;
			this.x=x;
			this.length=length;
		}
	}
}
