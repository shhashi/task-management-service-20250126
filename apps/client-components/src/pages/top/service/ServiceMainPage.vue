<template>
  <v-app>
    <ServiceMain :title="title" @change:view-type="changeViewType">
      <Home v-if="viewType == ViewTypes.Home"></Home>
      <TaskSearch v-if="viewType == ViewTypes.SearchTask"></TaskSearch>
      <MyPersonHour v-if="viewType == ViewTypes.MyPersonHour"></MyPersonHour>
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

const viewType = ref<ViewTypes>(ViewTypes.Home);
const title = ref<string>("ホーム");

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
      title.value = value.project!!.projectId;
      viewType.value = ViewTypes.Project;
      break;
  }
}
</script>
