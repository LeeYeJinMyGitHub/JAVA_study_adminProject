import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex);

export default new Vuex.Store({
  state:{
    test1: '',
    test2: '',
  },
  mutations: {
    SET_TEST1(state, value){
        state.test1 = value;
    },
    SET_TEST2(state, value){
        state.test2 = value;
    },
  },
  actions: {
      //비동기 호출
      TIME({commit}, value){
          return setTimeout(() => {
              commit('SET_TEST1', value);
          }, 1000);
      }
  },
  getters: {}
})