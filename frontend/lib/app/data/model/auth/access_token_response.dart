class AccessTokenResponse {
  final String accessToken;

  AccessTokenResponse({required this.accessToken});

  factory AccessTokenResponse.fromJson(Map<String, dynamic> json) {
    return AccessTokenResponse(
      accessToken: json['accessToken'],
    );
  }
}
