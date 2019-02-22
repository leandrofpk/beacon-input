CREATE TABLE entropy (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  raw_data longtext NOT NULL,
  chain varchar(20) NOT NULL,
  origin varchar(20) NOT NULL,
  output_value longtext NULL,
  previous_output_value longtext NULL,
  seed_value longtext NULL,
  signature_value longtext NULL,
  status_code varchar(20) NULL,
  time_stamp datetime NOT NULL,
  version_beacon varchar(20) NOT NULL,
  frequency varchar(3) NOT NULL,
  unix_time_stamp bigint(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;