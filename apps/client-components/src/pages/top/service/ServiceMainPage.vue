<template>
  <v-app>
    <ServiceMain :title="title" @change:view-type="changeViewType">
      <Home
        v-if="viewType == ViewTypes.Home"
        @change:view-type="changeViewType"
      ></Home>
      <TaskSearch v-if="viewType == ViewTypes.SearchTask"></TaskSearch>
      <MyPersonHour v-if="viewType == ViewTypes.MyPersonHour"></MyPersonHour>
      <ProjectDetail
        v-if="viewType == ViewTypes.Project"
        :project-id="projectId"
        @change:view-type="changeViewType"
      ></ProjectDetail>
      <TaskDetail v-if="viewType == ViewTypes.TaskDetail"></TaskDetail>
    </ServiceMain>
  </v-app>
</template>

<script setup lang="ts">
import ServiceMain from "@/atomic/templates/ServiceMain.vue";
import { ref } from "vue";
import { type ViewType, ViewTypes } from "@/lib/view/ViewType";
import Home from "@/atomic/organisms/view/Home.vue";
import MyPersonHour from "@/atomic/organisms/view/MyPersonHour.vue";
import TaskSearch from "@/atomic/organisms/view/TaskSearch.vue";
import TaskDetail from "@/atomic/organisms/view/TaskDetail.vue";
import ProjectDetail from "@/atomic/organisms/view/ProjectDetail.vue";

const viewType = ref<ViewTypes>(ViewTypes.Home);
const title = ref<string>("ホーム");
const projectId = ref<string>();

function changeViewType(value: ViewType) {
  switch (value.viewType) {
    case ViewTypes.Home:
      title.value = "ホーム";
      viewType.value = ViewTypes.Home;
      break;
    case ViewTypes.SearchTask:
      title.value = "タスク検索";
      viewType.value = ViewTypes.SearchTask;
      break;
    case ViewTypes.MyPersonHour:
      title.value = "My工数";
      viewType.value = ViewTypes.MyPersonHour;
      break;
    case ViewTypes.Project:
      title.value =
        "プロジェクト: " +
        value.project!!.projectId +
        " " +
        value.project!!.projectName;
      viewType.value = ViewTypes.Project;
      projectId.value = value.project!!.projectId;
      break;
    case ViewTypes.TaskDetail:
      title.value = "タスク: " + value.task!!.taskId;
      viewType.value = ViewTypes.TaskDetail;
      break;
  }
}
</script>
