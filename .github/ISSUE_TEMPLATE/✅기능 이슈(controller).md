---
name: "✅기능 이슈(controller)"
about: 기능 작업 사항을 입력해주세요.
title: "[feature] 이슈"
labels: ''
assignees: ''

---

# ✅ {리소스명} Controller CRUD 구현

## Description
{리소스명}에 대한 REST API Endpoint를 구현합니다.  
사용자가 {리소스명} 데이터를 생성, 조회, 수정, 삭제할 수 있도록 Controller 레벨의 처리를 진행합니다.  
요청/응답은 DTO를 사용하며, Swagger 문서화를 포함합니다.

## To Do — API 구현
- [ ] {리소스명} 생성 기능 (`POST /api/{resources}`)
- [ ] {리소스명} 단일 조회 기능 (`GET /api/{resources}/{id}`)
- [ ] {리소스명} 목록 조회 기능 (`GET /api/{resources}`)
- [ ] {리소스명} 수정 기능 (`PUT /api/{resources}/{id}`)
- [ ] {리소스명} 삭제 기능 (`DELETE /api/{resources}/{id}`)

## To Do — Controller 상세 작업
- [ ] Request DTO → Service 호출 → Response DTO 반환
- [ ] HTTP Status Code 명확히 설정 (ex. 생성: 201, 삭제: 204)
- [ ] 예외 발생 시 적절한 Error Response 반환
- [ ] Swagger `@Operation(summary, description)` 적용

## ETC
- 인증 적용은 필요 시 추후 `@AuthenticationPrincipal` 형태로 적용
