<template>
  <div>
    <RandomValue :categories="categories" ref="randomValue" class="d-grid gap-3 mb-3"/>
    <br>
    <button class="btn btn-info mb-3 btn-lg" @click="showValues = !showValues">Edit</button>
    <br>
    <div v-if="showValues">
      <CategoryForm ref="categoryForm" @refresh-categories="refreshCategories" class="mb-3"/>
      <ValueForm :categories="categories" ref="valueForm" @refresh-categories="refreshCategories" class="mb-3"/>
      <div>
        <div v-for="(category, index) in categories" :key="index">
          <div class="container">
            <div class="row p-3">
              <h3>Category {{ index + 1 }}</h3>
            </div>
            <div class="row p-3 border">
              <div class="col">
                <h4>Name</h4>
              </div>
              <div class="col">
                <h4>Description</h4>
              </div>
              <div class="col col-md-3">
                <h4>Action</h4>
              </div>
            </div>
            <div class="row p-3 border">
              <div class="col">
                {{ category.name }}
              </div>
              <div class="col">
                {{ category.description }}
              </div>
              <div class="btn-group col col-md-3">
                <button class="btn btn-danger btn-sm" @click="deleteCategory(category.id)">Delete</button>
                <button class="btn btn-warning btn-sm" @click="prepareCategoryForUpdate(category)">Update</button>
              </div>
            </div>
            <div class="row p-3 border">
              <div v-for="(value, index) in category.values" :key="index">
                <div class="container">
                  <div class="row p-3">
                    <h5>Value {{ index + 1 }}</h5>
                  </div>
                  <div class="row p-3 border">
                    <div class="col">
                      <h6>Name</h6>
                    </div>
                    <div class="col">
                      <h6>Description</h6>
                    </div>
                    <div class="col col-md-3">
                      <h6>Picture</h6>
                    </div>
                    <div class="col col-md-2">
                      <h6>Action</h6>
                    </div>
                  </div>
                  <div class="row p-3 border">
                    <div class="col">
                      {{ value.name }}
                    </div>
                    <div class="col">
                      {{ value.description }}
                    </div>
                    <div class="col col-md-3">
                      <img :src="value.picture" alt="image" class="image-fit"/>
                    </div>
                    <div class="btn-group col col-md-2">
                      <button class="btn btn-danger btn-sm value-button" @click="deleteValue(value.id)">Delete</button>
                      <button class="btn btn-warning btn-sm value-button" @click="prepareValueForUpdate(value)">Update</button>
                    </div>
                  </div>
                  <div class="row p-3">
                    <br>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import apiClient from '../services/api';
import CategoryForm from './CategoryForm.vue';
import ValueForm from './ValueForm.vue';
import RandomValue from './RandomValue.vue';

export default {
  data() {
    return {
      categories: [],
      showValues: false
    };
  },
  components: {
    CategoryForm, ValueForm, RandomValue
  },
  mounted() {
    this.refreshCategories();
  },
  methods: {
    refreshCategories() {
      apiClient.get('/api/1/random/category')
          .then(response => {
            this.categories = response.data;
          })
          .catch(error => {
            console.error('Error fetching categories:', error);
          });
    },
    deleteCategory(categoryId) {
      apiClient.delete(`/api/1/random/category/delete/${categoryId}`)
          .then(() => {
            console.log('Category deleted');
            this.refreshCategories();
          })
          .catch(error => {
            console.error('Error deleting category:', error);
          });
    },
    prepareCategoryForUpdate(category) {
      this.$refs.categoryForm.populateCategoryFormForUpdate(category);
    },
    deleteValue(valueId) {
      apiClient.delete(`/api/1/random/value/delete/${valueId}`)
          .then(() => {
            console.log('Value deleted');
            this.refreshCategories();
          })
          .catch(error => {
            console.error('Error deleting value:', error);
          });
    },
    prepareValueForUpdate(value) {
      this.$refs.valueForm.populateValueFormForUpdate(value);
      window.scrollTo({ top: 0, left: 0, behavior: 'smooth' });
    }
  }
};
</script>

<style>
.image-fit{
  height: 100%;
  width: 100%;
  object-fit: cover;
}
.value-button{
  white-space: normal !important;
  height: 40pt;
  width: 50pt;
}
</style>