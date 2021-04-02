/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */

import Component from '@glimmer/component';
import config from 'yavin-ui/config/environment';

export default class TopBarComponent extends Component {
  /**
   * @property {String} loggedInUser - logged in User's id
   */
  loggedInUser = config.navi.user;

  rootURL = config.rootURL;
}
