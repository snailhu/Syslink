package GridCP.core.gogsDomain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 255)
	private String  lower_name;
	
	@Column(length = 255)
	private String  name;
	
	@Column(length = 255)
	private String full_name;
	
	@Column(length = 255)
	private String email;
	
	@Column(length = 255)
	private String passwd;
	
	private  int login_type;
	
	private  int login_source;
	
	@Column(length = 255)
	private String login_name;
	
	private int type;
	
	@Column(length = 255)
	private String location;
	
	@Column(length = 255)
	private String website;
	
	@Column(length = 10)
	private String rands;
	
	@Column(length = 10)
	private String salt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	private int last_repo_visibility;
	
	private int max_repo_creation;
	
	private int is_active;
	
	private int is_admin;
	
	private int allow_git_hook;
	
	private int allow_import_local;
	
	@Column(length = 2048)
	private String avatar;
	
	@Column(length = 255)
	private String avatar_email;
	
	private int use_custom_avatar;
	
	private int num_followers;
	
	private int num_followings;
	
	private int num_stars;
	
	private int num_repos;
	
	@Column(length = 255)
	private String description;
	
	private int num_teams;
	
	private int num_members;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLogin_source() {
		return login_source;
	}

	public void setLogin_source(int login_source) {
		this.login_source = login_source;
	}
	
	public String getLower_name() {
		return lower_name;
	}

	public void setLower_name(String lower_name) {
		this.lower_name = lower_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getLogin_type() {
		return login_type;
	}

	public void setLogin_type(int login_type) {
		this.login_type = login_type;
	}



	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getRands() {
		return rands;
	}

	public void setRands(String rands) {
		this.rands = rands;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getLast_repo_visibility() {
		return last_repo_visibility;
	}

	public void setLast_repo_visibility(int last_repo_visibility) {
		this.last_repo_visibility = last_repo_visibility;
	}

	public int getMax_repo_creation() {
		return max_repo_creation;
	}

	public void setMax_repo_creation(int max_repo_creation) {
		this.max_repo_creation = max_repo_creation;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	public int getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(int is_admin) {
		this.is_admin = is_admin;
	}

	public int getAllow_git_hook() {
		return allow_git_hook;
	}

	public void setAllow_git_hook(int allow_git_hook) {
		this.allow_git_hook = allow_git_hook;
	}

	public int getAllow_import_local() {
		return allow_import_local;
	}

	public void setAllow_import_local(int allow_import_local) {
		this.allow_import_local = allow_import_local;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar_email() {
		return avatar_email;
	}

	public void setAvatar_email(String avatar_email) {
		this.avatar_email = avatar_email;
	}

	public int getUse_custom_avatar() {
		return use_custom_avatar;
	}

	public void setUse_custom_avatar(int use_custom_avatar) {
		this.use_custom_avatar = use_custom_avatar;
	}

	public int getNum_followers() {
		return num_followers;
	}

	public void setNum_followers(int num_followers) {
		this.num_followers = num_followers;
	}

	public int getNum_followings() {
		return num_followings;
	}

	public void setNum_followings(int num_followings) {
		this.num_followings = num_followings;
	}

	public int getNum_stars() {
		return num_stars;
	}

	public void setNum_stars(int num_stars) {
		this.num_stars = num_stars;
	}

	public int getNum_repos() {
		return num_repos;
	}

	public void setNum_repos(int num_repos) {
		this.num_repos = num_repos;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNum_teams() {
		return num_teams;
	}

	public void setNum_teams(int num_teams) {
		this.num_teams = num_teams;
	}

	public int getNum_members() {
		return num_members;
	}

	public void setNum_members(int num_members) {
		this.num_members = num_members;
	}
	
	
	
	
}
