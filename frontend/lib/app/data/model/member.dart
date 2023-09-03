class Member {

  int id;
  String email;
  String displayName;
  String profileImage;

  Member({ required this.id, required this.email, required this.displayName, required this.profileImage });

  factory Member.fromJson(Map<String, dynamic> json) {
    return Member(
      id: json['id'],
      email: json['email'],
      displayName: json['displayName'],
      profileImage: json['profileImage'],
    );
  }

  Map<String, dynamic> toJson(){
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['email'] = this.email;
    data['profileImage'] = this.profileImage;
    data['displayName'] = this.displayName;
    return data;
  }
}