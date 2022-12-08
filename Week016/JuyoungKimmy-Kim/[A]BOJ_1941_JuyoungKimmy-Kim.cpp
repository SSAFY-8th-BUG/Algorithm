// dfs + backtracking 으로 구현했는데 
// 도중에 가지를 뻗어나가는 경우 검사하지 못함

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int dy[] = { 0,1,0,-1 };
const int dx[] = { 1,0,-1,0 };
const int N = 5;

char map[N][N];
bool selected[N*N];
int ans;
vector<pair<int, int> >v;

void input() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
		}
	}
}

bool check() {
	queue<pair<int, int> >q;
	q.push(make_pair(v[0].first, v[0].second));

	bool flag[7] = { false, };
	flag[0] = true;

	int cnt = 1;
	while (!q.empty()) {
		int y = q.front().first;
		int x = q.front().second;
		q.pop();

		for (int k = 1; k < 7; k++) {
			if (flag[k]) continue;
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny == v[k].first && nx == v[k].second) {
					q.push(make_pair(v[k].first, v[k].second));
					flag[k] = true;
					cnt++;
				}
			}
		}
	}
	if (cnt == 7) return true;
	else return false;
}

void dfs(int idx, int lee, int lim) {
	if (lee + lim == 7) {
		if (lim > lee) return;
		if (check()) ans++;
		return;
	}

	for (int i = idx; i < 25; i++) {
		if (selected[i]) continue;
		int r = i / 5;
		int c = i % 5;
		v.push_back(make_pair(r, c));
		selected[i] = true;

		if (map[r][c] == 'Y') dfs(i + 1, lee, lim + 1);
		else dfs(i + 1, lee + 1, lim);
		selected[i] = false;
		v.pop_back();
	}
}

void solution() {
	input();
	dfs(0, 0, 0);
}	

int main(int argc, char** argv) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	solution();
	cout << ans << endl;

	return 0;
}