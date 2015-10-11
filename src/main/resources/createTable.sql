CREATE TABLE `portfolio` (
  `portfolio_no` int(11) NOT NULL,
  `portfolio_name` varchar(50) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `work_date` datetime NOT NULL,
  PRIMARY KEY (`portfolio_no`)
);

CREATE TABLE `project` (
  `project_no` int(11) NOT NULL,
  `project_name` varchar(100) NOT NULL,
  `participation_part` varchar(50) DEFAULT NULL,
  `date` date NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `del_yn` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`project_no`),
  UNIQUE KEY `project_no_UNIQUE` (`project_no`)
);
