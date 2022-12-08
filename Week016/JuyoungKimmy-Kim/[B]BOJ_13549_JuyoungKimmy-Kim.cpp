// 순간 이동은 걷는 이동보다 빠르기 때문에
// 우선 순위가 항상 더 높음 

#include <iostream>
#include <queue>

using namespace std;

const int MAX = 100001;
int N, K;
bool visited[MAX];

int bfs() {

	//현재 시간, 위치를 넣음
	priority_queue < pair<int, int>, vector<pair<int, int> >, greater <pair<int, int> >> pq;
	pq.push({ 0,N });
	visited[N] = true;

	while (!pq.empty()) {
		int time = pq.top().first;
		int x = pq.top().second;
		pq.pop();

		if (x == K) return time;
		
		if (x * 2 < MAX && !visited[x * 2]) {
			pq.push({ time, x * 2 });
			visited[x * 2] = true;
		}

		if (x + 1 < MAX && !visited[x + 1]) {
			pq.push({ time + 1, x + 1 });
			visited[x + 1] = true;
		}

		if (x - 1 >= 0 && !visited[x - 1]) {
			pq.push({ time + 1, x - 1 });
			visited[x - 1] = true;
		}

	}
}

int main(int argc, char** argv) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> K;
	cout << bfs() << endl;

}