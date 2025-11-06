# 🗓️ 일정 관리 API 명세서

> 일정 등록, 조회, 수정, 삭제 및 댓글 등록 기능을 제공합니다.

---

## 🔗 기본 정보
| 항목 | 내용 |
|------|------|
| **Base URL** | `http://localhost:8080` |
| **Content-Type** | `application/json` |

---

## 📅 Schedule 관련 API

### ✅ 1. 일정 조회 (Get Schedule)
**GET** `/schedules/{id}`

#### ✔️ Request
| 파라미터 | 타입 | 설명 |
|-----------|------|------|
| `id` | Long | 조회할 일정의 ID |

### 📝 2. 일정 등록 (Create Schedule)

**POST** `/schedules`

##### ✔️ Request Body
|           |  |
|-----------|----|
| "title"   | "홍길동전"|
| "content" | "재밋네요"|
| "name"    | "최정혁"|
| "password" | "1111"|


#### ✔️ Response (예시)
|           |  |
|-----------|----|
| "title"   | "홍길동전"|
| "content" | "재밋네요"|
| "name"    | "최정혁"|
### ✏️ 3. 일정 수정 (Update Schedule)

**PUT** `/schedules/{id}`

#### ✔️ Request

| 파라미터 | 타입 | 설명         |
|-----------|------|------------|
| `id` | Long | 수정할 일정의 ID | 

#### ✔️ Request Body
|           |  |
|-----------|----|
| "title"   | "홍길동전"|
| "name"    | "최정혁"|
| "password" | "1111"|

#### ✔️ Response (예시)

|           |  |
|-----------|----|
| "title"   | "홍길동전"|
| "name"    | "최정혁"|



### ❌ 4. 일정 삭제 (Delete Schedule)

**DELETE** `/schedules/{id}`

#### ✔️ Request

| 파라미터 | 타입 | 설명         |
|-----------|------|------------|
| `id` | Long | 삭제할 일정의 ID

✔️ Request Body

|           |  |
|-----------|----|
| "password" | "1111"|



## 💬 Comment 관련 API
### 💭 5. 댓글 등록 (Create Comment)

**POST**  `/schedules/{scheduleId}/comments`

#### ✔️ Request

| 파라미터 | 타입 | 설명         |
|-----------|------|------------|
| `id` | Long | 삭제할 일정의 ID

✔️ Request Body

|           |           |
|-----------|-----------|
| "leaveComment"   | "잘 봤습니다." |
| "writer" | "최정혁"     |
| "password" | "1111"    |

✔️ Response (예시)

|           |           |
|-----------|-----------|
| "leaveComment"   | "잘 봤습니다." |
| "writer" | "최정혁"     |


---

# 🏗️ 기술 스택

| 구분                  | 기술       |
| ------------------- |----------|
| **Language**        | Java 17+ |
| **Framework**       | Spring Boot |
| **Database**        |  MySQL   |
| **API Spec Format** | JSON     |
| **IDE**             | IntelliJ IDEA |
---



# 🗓️ 일정 관리 앱 ERD
![](https://velog.velcdn.com/images/fluxing/post/2ecfab69-ccbc-4aab-9e3c-e6c44c3302b0/image.png)




