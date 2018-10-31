# Site Crawler

- 프로젝트명 : Site Crawler
- 관련 기술 : Jsoup, RecyclerView,Glide
- Github : https://github.com/WooVictory/Site-Crawler
- 내용 : 현재 실시간으로 검색어에 오르 내리는 키워드들이 무엇인지 궁금한데, 네이버나  다른 웹 사이트에 들어가지 않고 이 앱을 통해서 실시간 검색 순위를 가져와서 한눈에 보기 쉽도록 구현했습니다. 또한, 현재 상영 중인 영화나 네이버의 실시간 뉴스들을 카테고리별로 불러와서 사용자들이 쉽게 볼 수 있도록 구현했습니다. 이러한 정보들을 사용자들이 자주 보는 정보인데, 네이버에 들어가지 않고도 하나의 앱으로 필수적인 정보들을 볼 수 있어서 번거로움을 줄일 수 있다고 생각합니다.



## 구현과정

1. Jsoup 라이브러리 사용

   : 네이버의 실시간 검색 순위를 받아와야 하기 때문에 웹의 정보를 파싱해서 가져올 수 있도록 Jsoup을 사용했습니다. Jsoup.connect(htmlUrl).get()을 통해서 웹 정보를 가져온 후 select("span.ah_k")의 쿼리를 통해서 실시간 검색어 40개를 불러와서 20개를 보여줍니다. 또한, 네이버 영화 페이지로부터 정보를 불러와서 현재 상영 중인 영화의 이미지와 이름, 개봉 날짜, 감독 이름을 사용자에게 보여줍니다. data class에는 url 변수를 두어, 해당 링크를 string으로 저장하여 리스트에 있는 item을 click 하면 웹뷰로 넘어가서 영화에 대한 상세한 정보를 확인할 수 있습니다. 마지막으로 네이버 뉴스의 정보를 불러와서 카테고리 별로 구분했습니다. 영화 목록과 마찬가지로 data class의 url 관련 변수를 통해서 리스트의 item을 click 하면 웹뷰로 넘어가 기사를 읽을 수 있습니다. 

1. RecyclerView 사용

   : html을 파싱해서 가져온 정보들을 단순히 View에 보여주지 않고 RecyclerView를 이용해서 효율적인 리스트 출력을 가능하게 하였습니다. ViewHolder의 의무적 사용과 LayoutManager를 사용함으로써 다양한 리스트를 구현할 수 있는 RecyclerView를 사용했습니다.

2. Glide 사용
: 이미지 로드 라이브러리로 Glide를 사용했습니다. 큰 용량의 간단하게 이미지를 처리하기 위해 사용!

<!--
지원 이유
  이전부터 크롤링과 관련된 주제에 관심이 있었지만, 도전해 본 적은 없었습니다. 마침 핵데이에 소개된 주제를 보고 간단한 애플리케이션을 제작해보고 싶다는 마음이 생겼습니다. 그래서 저는 네이버에 들어가지 않고 네이버 실시간 검색어를 크롤링하여 20개까지 리스트로 출력하여 보여주는 앱을 제작했습니다. 

  아직 완성되지 않았지만, 저는 이번 핵데이에 지원을 해서 미완성 된 애플리케이션을 제작하여 사람들에게 편의를 주면서 복잡하지 않은 애플리케이션을 만들 싶습니다.  또한, 클라이언트 측에서 크롤링을 하는 방법에 대해서도 더욱 자세하게 알아보고 싶어서 지원하게 되었습니다. 
-->