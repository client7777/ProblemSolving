# [Gold III] 변신로봇 - 14630 

[문제 링크](https://www.acmicpc.net/problem/14630) 

### 성능 요약

메모리: 268756 KB, 시간: 520 ms

### 분류

데이크스트라, 그래프 이론, 최단 경로

### 제출 일자

2025년 5월 2일 06:45:18

### 문제 설명

<p style="text-align: center;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14630/1.png" style="height:194px; width:205px"></p>

<p>승균이는 변신로봇에 심취해있었다. 한 분야가 극에 달한 사람은 그것을 통해 세상을 이해한다는 말이 있는데, 승균이가 바로 그러했다. 승균이는 시시때때로 감정이 변하는 사람들을 보면서 사람은 변신로봇과 같다고 생각했다. 또한, 세계의 흐름은 거대한 변신로봇을 조립하는 과정이며, 그 흐름 속에서 우리의 역할은 변신로봇의 부품으로써 다른 부품들과 올바르게 맞물려있는 것에 있다고 믿고 있었다. 그러나 이런 승균이를 보고 있던 승균이의 선생님 준하는 마음이 편치 않았다. 왜냐하면, 자신이 승균이에게 변신로봇을 소개 시켜준 것은 변신로봇을 통해 과학과 수학에 관심을 갖게 하려는 의도였는데, 준하의 의도와는 달리 승균이가 변신로봇을 통해 철학가가 되어가고 있었기 때문이다. 보다 못한 준하는 변신로봇에 동전투입기를 박아버렸다.</p>

<p>이제 변신을 하기 위해선 동전을 넣어야 하는데, 로봇의 현재 상태에서 부품의 형태가 가까운 변신일수록 돈이 적게 들고 먼 형태일수록 돈이 많이 드는 구조이다. 부품의 형태는 숫자로 나타낼 수 있고, 로봇의 상태는 그 숫자들의 모임으로 나타낼 수 있다. 변신에 필요한 돈은 각 부품들의 숫자 차이를 제곱한 것의 합이 된다. 예를 들어 로봇의 현재 상태가 123이고 다른 상태가 222라고 한다면 변신에 필요한 돈은 (1-2)<sup>2</sup> + (2-2)<sup>2</sup> + (3-2)<sup>2</sup>인 2가 된다.</p>

<p>승균이는 수학을 전혀 못하기 때문에 이대로 가다간 패닉에 빠질지도 모른다. 불쌍한 승균이를 대신해 돈을 가장 적게 사용해서 승균이가 원하는 변신 상태를 만들어주자.</p>

### 입력 

 <p>첫째 줄에 변신로봇의 변신 상태의 개수 N이 주어진다. (1≤N≤1,000)</p>

<p>둘째 줄부터 N줄에 걸쳐 각 변신 상태에 대한 부품의 형태가 숫자로 주어진다. 부품의 형태의 길이는 100을 넘지 않는다. 길이가 다른 부품의 형태는 존재하지 않고, 부품의 형태는 0으로 시작할 수 있다.</p>

<p>다음 줄에 현재 변신 상태의 번호와 승균이가 원하는 변신 상태의 번호가 주어진다. 번호는 위에 입력받은 순서대로 1부터 번호가 매겨져 있다고 가정한다.</p>

### 출력 

 <p>첫째 줄에 현재 변신 상태로 승균이가 원하는 변신 상태를 만드는 대에 필요한 돈의 최솟값을 출력한다.</p>

