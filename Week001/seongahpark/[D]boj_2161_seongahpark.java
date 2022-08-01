import java.util.*;
import java.io.*;

public class Main {

	// 가장 위에있느 카드를 뽑고
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> yes = new LinkedList<Integer>(); // 카드 묶음
		Queue<Integer> no = new LinkedList<Integer>(); // 버린 카드
		for(int i=1; i<=n; i++) yes.add(i);
		
		int tmp;
		while(yes.size() > 1) {
			tmp = yes.poll();
			no.add(tmp);
			if(yes.size() == 1) break;
			tmp = yes.poll();
			yes.add(tmp);
		}
		
		for(int i : no) System.out.print(i + " ");
		System.out.print(yes.poll());
		br.close();
	}

}