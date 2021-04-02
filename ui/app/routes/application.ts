/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */
import Route from '@ember/routing/route';
import { inject as service } from '@ember/service';
import type NaviMetaService from 'navi-data/services/navi-metadata';
import type NaviUserService from 'navi-core/services/user';

export default class ApplicationRoute extends Route {
  @service
  declare naviMetadata: NaviMetaService;

  @service
  declare user: NaviUserService;

  /**
   * @method model
   * @override
   * @returns {Ember.RSVP.Promise}
   */
  async model() {
    await Promise.all([
      this.user.findOrRegister(),
      this.naviMetadata.loadMetadata(),
    ]);
  }
}
