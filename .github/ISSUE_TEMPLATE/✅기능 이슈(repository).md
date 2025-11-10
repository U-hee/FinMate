---
name: "✅기능 이슈(repository)"
about: 기능 작업 사항을 입력해주세요.
title: "[feature] 이슈"
labels: ''
assignees: ''

---
# ✅ {리소스명} Repository 데이터 접근 계층 구현

## Description
MyBatis Mapper와 XML을 기반으로 {리소스명} 데이터에 대한 저장/조회/수정/삭제 로직을 구현합니다.  
Service 계층에서 사용할 수 있도록 명확한 메서드 인터페이스를 제공합니다.

## To Do — Mapper Interface
- [ ] `{리소스명}Mapper` 인터페이스 생성
- [ ] {리소스명} 생성 메서드 (`insert{리소스명}`)
- [ ] {리소스명} 단일 조회 메서드 (`select{리소스명}ById`)
- [ ] {리소스명} 목록 조회 메서드 (`select{리소스명}List`)
- [ ] {리소스명} 수정 메서드 (`update{리소스명}`)
- [ ] {리소스명} 삭제 메서드 (`delete{리소스명}`)

## To Do — Mapper XML
- [ ] `resources/mapper/{리소스명}Mapper.xml` 파일 생성
- [ ] INSERT SQL 작성
- [ ] SELECT (단일/목록) SQL 작성
- [ ] UPDATE SQL 작성
- [ ] DELETE SQL 작성
- [ ] ResultMap 정의 (DTO 또는 Entity에 매핑)

## To Do — 매핑/구조 고려사항
- [ ] DTO ↔ Entity 변환 적용 방식 검토
- [ ] 컬럼명/필드명 SnakeCase ↔ CamelCase 매핑 확인
- [ ] N+1 문제 발생 가능성 체크 (JOIN 필요 시 미리 설계)

## ETC
- SQL 가독성을 위해 공통 prefix (`<sql id="Base_Columns">`) 활용 가능
- 복잡한 조회 조건이 필요한 경우 별도 `CustomMapper` 확장 고려
