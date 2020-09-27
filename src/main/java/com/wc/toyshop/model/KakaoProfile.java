package com.wc.toyshop.model;

import lombok.Data;

//OAuth2 client 라이브러리를 사용하지 않고 카카오 로그인할때 사용할때 씀
@Data
public class KakaoProfile {

	public Integer id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;

	@Data
	public class Properties {

		public String nickname;
		public String profile_image;
		public String thumbnail_image;
	}
	@Data
	public class KakaoAccount {

		public Boolean profile_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;
		
		@Data
		public class Profile {

			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
		}
	}
}
