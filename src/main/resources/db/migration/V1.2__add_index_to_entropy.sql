ALTER TABLE entropy
ADD INDEX indx_timestamp (time_stamp ASC),
ADD INDEX index_unixtimestamp (unix_time_stamp ASC);
