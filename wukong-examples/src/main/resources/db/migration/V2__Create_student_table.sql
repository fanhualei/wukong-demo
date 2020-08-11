DROP TABLE IF EXISTS wk_student;

create table wk_student (
  student_id     BIGINT unsigned         NOT NULL AUTO_INCREMENT    COMMENT '学生id',
  student_name   varchar(255)            NOT NULL                   COMMENT '学生姓名',
  student_age    int                                                COMMENT '学生年龄',
  student_sex    int                                                COMMENT '学生性别',

  PRIMARY KEY (student_id)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生表';



DROP TABLE IF EXISTS wk_student_info;

create table wk_student_info (
  student_info_id     BIGINT unsigned         NOT NULL AUTO_INCREMENT    COMMENT '学生详细id',
  student_id          BIGINT unsigned         NOT NULL                   COMMENT '学生id',
  qq                  varchar(255)                                       COMMENT 'qq号',
  wx                  varchar(255)                                       COMMENT 'wx号',
  mobil               varchar(255)                                       COMMENT '手机号',
  address             varchar(255)                                       COMMENT '地址',

  PRIMARY KEY (student_info_id)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生详细表';



DROP TABLE IF EXISTS wk_student_scores;

create table wk_student_scores (
  student_scores_id   BIGINT unsigned         NOT NULL AUTO_INCREMENT    COMMENT '学生详细id',
  student_info_id     BIGINT unsigned         NOT NULL                   COMMENT '学生id',
  course_id           BIGINT unsigned         NOT NULL                   COMMENT '课程编号',
  score               decimal(15,4)           NOT NULL                   COMMENT '数值：带小数点的',

  PRIMARY KEY (student_scores_id),
  UNIQUE KEY `wk_student_scores_unique` (`student_info_id`,`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生成绩表';



DROP TABLE IF EXISTS wk_course;
create table wk_course (
  course_id     BIGINT unsigned         NOT NULL AUTO_INCREMENT          COMMENT '课程id',
  course_name   varchar(255)            NOT NULL                         COMMENT '课程',
  gmt_create    DATETIME                DEFAULT CURRENT_TIMESTAMP NULL   COMMENT '记录创建时间',
  gmt_modified  DATETIME                DEFAULT CURRENT_TIMESTAMP NULL   COMMENT '记录修改时间',
  PRIMARY KEY (course_id),
  UNIQUE KEY `wk_course_unique` (`course_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程表';