package week007;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Eboj_1744_수묶기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		LinkedList<Integer> list = new LinkedList<Integer>(); // 양수
		LinkedList<Integer> list2 = new LinkedList<Integer>(); // 음수
		boolean isZeroExist = false;

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0)
				isZeroExist = true;
			else if (input < 0)
				list2.add(input);
			else if (input > 0)
				list.add(input);

		}
		Collections.sort(list, (e1, e2) -> e2 - e1);
		Collections.sort(list2);

		int sum = 0;
		while (list.size() > 1) {
			if (list.get(0) == 1 || list.get(1) == 1)
				sum += list.get(0) + list.get(1);
			else
				sum += list.get(0) * list.get(1);

			list.remove(0);
			list.remove(0);
		}
		while (list2.size() > 1) {
			sum += list2.get(0) * list2.get(1);
			list2.remove(0);
			list2.remove(0);
		}
		if (list.size() == 1)
			sum += list.get(0);
		if (list2.size() == 1 && !isZeroExist)
			sum += list2.get(0);

		System.out.println(sum);

	}

}
