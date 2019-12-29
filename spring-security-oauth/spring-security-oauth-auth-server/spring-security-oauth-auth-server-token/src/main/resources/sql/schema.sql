CREATE TABLE IF NOT EXISTS `oauth_access_token` (
  authentication_id VARCHAR(256) PRIMARY KEY,
  authentication BLOB,
  token_id VARCHAR(256),
  token BLOB,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  refresh_token VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
  token_id VARCHAR(256) PRIMARY KEY,
  token BLOB,
  authentication BLOB
);

CREATE TABLE IF NOT EXISTS `oauth_authorization_code` (
  code VARCHAR(256) PRIMARY KEY,
  authentication BLOB
);