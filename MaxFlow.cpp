#include <iostream>
#include <vector>
#include <queue>
#include <limits>

using namespace std;

const int INF = numeric_limits<int>::max();


struct Edge {
    int to, capacity, flow, reverseEdge;
};

class MaxFlowSolver {
public:
    MaxFlowSolver(int verticesCount);

    void addEdge(int from, int to, int capacity);

    int findMaxFlow(int source, int sink);

private:
    int verticesCount;
    vector<vector<Edge>> graph;
    vector<int> level, nextEdge;

    bool bfs(int source, int sink);
    int dfs(int current, int sink, int minCapacity);
};

MaxFlowSolver::MaxFlowSolver(int verticesCount) : verticesCount(verticesCount) {
    graph.resize(verticesCount);
    level.resize(verticesCount);
    nextEdge.resize(verticesCount);
}

void MaxFlowSolver::addEdge(int from, int to, int capacity) {
    Edge forwardEdge = {to, capacity, 0, static_cast<int>(graph[to].size())};
    Edge reverseEdge = {from, 0, 0, static_cast<int>(graph[from].size())};

    graph[from].push_back(forwardEdge);
    graph[to].push_back(reverseEdge);
}

bool MaxFlowSolver::bfs(int source, int sink) {
    fill(level.begin(), level.end(), -1);
    level[source] = 0;

    queue<int> q;
    q.push(source);

    while (!q.empty()) {
        int current = q.front();
        q.pop();

        for (const Edge& edge : graph[current]) {
            if (level[edge.to] == -1 && edge.flow < edge.capacity) {
                level[edge.to] = level[current] + 1;
                q.push(edge.to);
            }
        }
    }

    return level[sink] != -1;
}

int MaxFlowSolver::dfs(int current, int sink, int minCapacity) {
    if (current == sink) {
        return minCapacity;
    }

    for (int& i = nextEdge[current]; i < graph[current].size(); ++i) {
        Edge& edge = graph[current][i];

        if (level[edge.to] == level[current] + 1 && edge.flow < edge.capacity) {
            int flow = dfs(edge.to, sink, min(minCapacity, edge.capacity - edge.flow));

            if (flow > 0) {
                edge.flow += flow;
                graph[edge.to][edge.reverseEdge].flow -= flow;
                return flow;
            }
        }
    }

    return 0;
}

int MaxFlowSolver::findMaxFlow(int source, int sink) {
    int maxFlow = 0;

    while (bfs(source, sink)) {
        fill(nextEdge.begin(), nextEdge.end(), 0);
        int flow;

        while ((flow = dfs(source, sink, INF)) > 0) {
            maxFlow += flow;
        }
    }

    return maxFlow;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int Z;
    cin >> Z;

    for (int z = 0; z < Z; ++z) {
        int V, E;
        cin >> V >> E;

        MaxFlowSolver solver(V);

        for (int i = 0; i < E; ++i) {
            int u, v, c;
            cin >> u >> v >> c;
            solver.addEdge(u, v, c);
        }

        int source = 0;
        int sink = V - 1;

        int maxFlow = solver.findMaxFlow(source, sink);

        cout << maxFlow << endl;
    }

    return 0;
}
