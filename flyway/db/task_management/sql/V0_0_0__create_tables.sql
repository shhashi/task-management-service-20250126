-- 工数
-- * RestoreFromTempTable
create table person_hours
(
    person_hour_id bigint not null,
    account_ulid   varchar(26),
    task_id        varchar(100),
    person_hour    integer,
    working_day    date,
    created_at     date,
    constraint person_hours_PKC primary key (person_hour_id)
);

-- status
-- * RestoreFromTempTable
create table "ステータス"
(
    status_id   integer not null,
    status_name varchar(100),
    created_at  date,
    deleted_at  date,
    constraint "ステータス_PKC" primary key (status_id)
);

-- タスク
-- * RestoreFromTempTable
create table tasks
(
    task_id      varchar(100) not null,
    task_name    varchar(100),
    description  text,
    finish_date  date,
    account_ulid varchar(26),
    project_id   varchar(100),
    status_id    integer,
    created_at   date,
    deleted_at   date,
    constraint tasks_PKC primary key (task_id)
);

-- 担当
-- * RestoreFromTempTable
create table roles
(
    role_id    integer      not null,
    role_name  varchar(100) not null,
    created_at date,
    updated_at date,
    deleted_at date,
    constraint roles_PKC primary key (role_id)
);

-- プロジェクト内担当
-- * RestoreFromTempTable
create table account_roles
(
    account_role_id integer      not null,
    account_ulid    varchar(26)  not null,
    project_id      varchar(100) not null,
    role_id         integer      not null,
    created_at      date,
    deleted_at      date,
    constraint account_roles_PKC primary key (account_role_id)
);

-- プロジェクト
-- * RestoreFromTempTable
create table projects
(
    project_id   varchar(100) not null,
    project_name varchar(100) not null,
    created_at   date,
    deleted_at   date,
    constraint projects_PKC primary key (project_id)
);

-- アカウント
-- * RestoreFromTempTable
create table accounts
(
    account_ulid varchar(26)  not null,
    account_name varchar(20)  not null,
    password     varchar(100) not null,
    created_at   date,
    deleted_at   date,
    constraint accounts_PKC primary key (account_ulid)
);
