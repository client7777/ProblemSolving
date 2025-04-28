# [Gold III] Coolest Ski Route - 16064 

[문제 링크](https://www.acmicpc.net/problem/16064) 

### 성능 요약

메모리: 38600 KB, 시간: 2132 ms

### 분류

방향 비순환 그래프, 다이나믹 프로그래밍, 플로이드–워셜, 그래프 이론, 최단 경로, 위상 정렬

### 제출 일자

2025년 4월 29일 06:49:49

### 문제 설명

<p>John loves winter. Every skiing season he goes heli-skiing with his friends. To do so, they rent a helicopter that flies them directly to any mountain in the Alps. From there they follow the picturesque slopes through the untouched snow.</p>

<p>Of course they want to ski on only the best snow, in the best weather they can get. For this they use a combined condition measure and for any given day, they rate all the available slopes.</p>

<p>Can you help them find the most awesome route?</p>

### 입력 

 <p>The input consists of:</p>

<ul>
	<li>one line with two integers n (2 ≤ n ≤ 1000) and m (1 ≤ m ≤ 5000), where n is the number of (1-indexed) connecting points between slopes and m is the number of slopes.</li>
	<li>m lines, each with three integers s, t, c (1 ≤ s, t ≤ n, 1 ≤ c ≤ 100) representing a slope from point s to point t with condition measure c.</li>
</ul>

<p>Points without incoming slopes are mountain tops with beautiful scenery, points without outgoing slopes are valleys. The helicopter can land on every connecting point, so the friends can start and end their tour at any point they want. All slopes go downhill, so regardless of where they start, they cannot reach the same point again after taking any of the slopes.</p>

### 출력 

 <p>Output a single number n that is the maximum sum of condition measures along a path that the friends could take.</p>

