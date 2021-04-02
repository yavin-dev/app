/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */
import Route from '@ember/routing/route';

export default class IndexRoute extends Route {
  redirect() {
    this.transitionTo('directory');
  }
}
